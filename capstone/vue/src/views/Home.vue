<template>
  <div class="home">
    <div class="posts">
      <post v-for="post in $store.state.posts" v-bind:key="post.postId" v-bind:post="post" />
    </div>
    <aside>
      <router-link
        v-bind:to="{ name: 'createForum' }"
        v-if="$store.state.token != ''"
        >Create Forum</router-link>
      <top-5-forums />
      <div v-show="$store.state.token != ''">
        <favorite-forums />
      </div>
    </aside>
  </div>
</template>

<script>
import Top5Forums from "../components/Top5Forums.vue";
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";
import interactionsService from "../services/InteractionsService";
import moderatorsService from "../services/ModeratorsService";
import favoriteService from "../services/FavoriteService";
import FavoriteForums from "../components/FavoriteForums.vue";

export default {
  name: "home",
  components: {
    Top5Forums,
    Post,
    FavoriteForums,
  },
  
  created() {
    postsService.getTop10().then((response) => {
      this.$store.commit("SET_POSTS", response.data);
    });
    if (this.$store.state.token != "") {
      interactionsService
        .getInteractions(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_INTERACTIONS", response.data);
          console.log("interactions added");
        });
      favoriteService
        .getFavorites(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_FAVORITES", response.data);
          console.log("favorites added");
        });
    }
    if (this.$store.state.token != "") {
      moderatorsService
        .getForumsModerated(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_FORUMS_MODERATED", response.data);
          console.log("forums moderated added");
        });
    }
  },
};
</script>

<style >
   .home{
    display: grid;
    grid-template-columns: 1fr 1fr 1fr ;
    grid-template-areas: "aside posts div.post-component";
    grid-gap: 20px;
    margin-top: 20px;
    
  }
  .posts{
    grid-area: posts;
  }


  aside{
    position: fixed;
    margin-top: 100px;
    top: 0;
    left: 0;
    bottom: 0;
    height: 100vh;
    border-right: 1px solid #cfcfcf;
    padding: 2px;
    background-color: rgb(255, 255, 255);
    width: 18%;
    }

</style>

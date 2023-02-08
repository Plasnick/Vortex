<template>
  <div class="home">
    <div class="body">
      <section id="sidebar">
        <top-5-forums /> 
        <favorite-forums />
      </section>
      <section id="content">
          <div class="header-nav">
            <div class="nav">
              <div class="aside-header">
                <router-link v-bind:to="{ name: 'createForum' }" v-if="$store.state.token != ''">Create Forum</router-link>
              </div>
            </div>
          </div>
        <div class="content">
          <div class="posts-container">
            <post v-for="post in $store.state.posts" v-bind:key="post.postId" v-bind:post="post" />
          </div>
        </div>
      </section>
    </div>
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

<style scoped>
#sidebar {
  overflow: hidden;
  width: 210px;
  height: 100%;
  float: left;
  position: sticky;
  top: 0;  
  border-right: 1px solid #D4D4D4;
  box-shadow: 4px 0px 4px -4px rgb(240, 240, 240);

}

#header-nav {
  position: sticky;
  top: 0;
  background-color: white;
  z-index: 2;
  width: calc(100% - 210px);
  padding: 10px;
  box-shadow: 0 4px 4px -4px rgb(240, 240, 240);
}



</style>

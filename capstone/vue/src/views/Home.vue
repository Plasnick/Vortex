<template>
  <div class="home">
    <div class="body">
      <h1>Home</h1>
      <p>You must be authenticated to see this</p>
      <search-forums />
      <h2>Top 10 Posts Today</h2>
      <post
        v-for="post in $store.state.posts"
        v-bind:key="post.postId"
        v-bind:post="post"
      />
    </div>

    <aside>
      <top-5-forums />
    </aside>
  </div>
</template>

<script>
import Top5Forums from "../components/Top5Forums.vue";
import SearchForums from "../components/SearchForums.vue";
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";
import interactionsService from "../services/InteractionsService";
import favoriteService from "../services/FavoriteService";

export default {
  name: "home",
  components: {
    Top5Forums,
    SearchForums,
    Post,
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
  },
};
</script>

<style scoped>
.home {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas: "sidebar body body body";
  grid-gap: 20px;
  margin-top: 20px;
}
.body {
  grid-area: body;
}
aside {
  grid-area: sidebar;
  border-top: 1px solid #000;
  border-right: 1px solid #000;
  border-bottom: 1px solid #000;
  border-radius: 10%;
  height: 100%;
  width: 200px;
  margin-right: 20px;
  padding-left: 10px;
  position: fixed;
  height: 60vh;
}
</style>

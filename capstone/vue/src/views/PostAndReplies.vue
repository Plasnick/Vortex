<template>
  <div class="post-and-replies">
    <post v-bind:post="post" />
    <replies-feed />
  </div>
</template>

<script>
import RepliesFeed from "../components/RepliesFeed.vue";
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";

export default {
  components: { RepliesFeed, Post },
  name: "post-and-replies",
  data() {
    return {
      post: {},
    };
  },
  created() {
    postsService.getPost(this.$route.params.id).then((response) => {
      this.post = response.data;
    });
  },
};
</script>

<style>
</style>
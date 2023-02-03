<template>
  <div class="post-and-replies">
    <post v-bind:post="post" />
    <replies-feed />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";
import RepliesFeed from "../components/RepliesFeed.vue";

export default {
  components: { Post, RepliesFeed },
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
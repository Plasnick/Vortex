<template>
  <div class="post-component">
    <div class="post-details">
      <h2>{{ post.title }}</h2>
      <h3>User: {{ post.userId }} Posted On: {{ post.postedAt }}</h3>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>{{ post.body }}</p>
    </div>
    <!-- reply button -->
  </div>
</template>

<script>
import PostsService from "../services/PostsService";
export default {
  name: "post-component",
  data() {
    return {
      post: {},
    };
  },
  created() {
    PostsService.getPost(this.$route.params.id).then((response) => {
      this.post = response.data;
    });
  },
};
</script>

<style>
</style>
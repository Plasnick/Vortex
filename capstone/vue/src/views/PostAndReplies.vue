<template>
  <div class="post-and-replies">
    <div class="post-details">
      <h2>{{ post.title }}</h2>
      <h3>User: {{ post.userId }} Posted On: {{ post.postedAt }}</h3>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
      <!-- need to add the upvoted and downvotes instead of score -->

      <p>{{ post.body }}</p>
    </div>
    <post-replies-feed />
  </div>
</template>

<script>
import PostRepliesFeed from "../components/PostRepliesFeed.vue";
import PostsService from "../services/PostsService";

export default {
  components: { PostRepliesFeed },
  name: "post-and-replies",
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
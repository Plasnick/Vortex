<template>
  <div id="post-replies-feed">
    <div
      class="reply"
      v-for="reply in repliesByPost"
      v-bind:key="reply.replyId"
    >
      <h4>User: {{ reply.userId }} Posted On: {{ reply.postedAt }}</h4>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
      <p>{{ reply.body }}</p>
    </div>
  </div>
</template>

<script>
import ReplyService from "../services/ReplyService";
export default {
  name: "post-replies-feed",
  data() {
    return {
      repliesByPost: [],
    };
  },
  created() {
    ReplyService.getPostReplies(this.$route.params.postId).then((response) => {
      this.repliesByPost = response.data;
    });
  },
};
</script>

<style>
</style>
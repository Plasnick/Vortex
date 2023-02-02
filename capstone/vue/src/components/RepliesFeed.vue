<template>
  <div id="replies-feed">
    <div class="create-post" v-on:submit.prevent="submitReply">
      <h3>Write Reply Here</h3>
      <form class="new-reply-form">
        <textarea
          placeholder="Reply to the post!"
          v-model="newPost.body"
          required
        ></textarea>
        <input
          type="text"
          placeholder="upload image"
          v-model="newPost.img_url"
        />
        <div class="actions">
          <button v-on:click="resetForm" type="button">Cancel</button>
          <button>Submit</button>
        </div>
      </form>
    </div>
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
import replyService from "../services/ReplyService";
export default {
  name: "replies-feed",
  data() {
    return {
      newReply: {
        userId: this.$store.state.user.id,
        postId: this.$route.params.id,
        body: "",
        img_url: null,
      },
      repliesByPost: [],
    };
  },
  methods: {
    submitReply() {
      replyService.createReply(this.newReply).then((response) => {
        if (response.status === 201) {
          this.$router.push({
            name: "postAndReplies",
            params: { id: this.$route.params.id },
          });
        }
      });
    },
  },
  created() {
    replyService.getPostReplies(this.$route.params.id).then((response) => {
      this.repliesByPost = response.data;
    });
  },
};
</script>

<style scoped>
#new-post-form {
  display: flex;
  flex-direction: column;
}
</style>
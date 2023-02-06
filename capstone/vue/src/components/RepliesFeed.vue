<template>
  <div id="replies-component">
    <h3>Write Reply Here</h3>
    <form class="create-reply">
      <div class="body">
        <textarea
          id="reply-body"
          placeholder="Reply to the post!"
          v-model="newReply.body"
          required
        ></textarea>
      </div>
      <div class="actions">
        <button v-on:click="submitReply()">Submit</button>
      </div>
    </form>

    <div
      class="replies-feed"
      v-for="reply in repliesByPost"
      v-bind:key="reply.commentId"
    >
      <h4>User: {{ reply.userId }} Posted On: {{ reply.postedAt }}</h4>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->

      <p>{{ reply.body }}</p>
      <delete-replies v-bind:postId="reply.postId" v-bind:commentId="reply.commentId" v-on:replyDeleted="removeReply(reply)"/>    </div>
  </div>
</template>

<script>
import replyService from "../services/ReplyService";
import DeleteReplies from './DeleteReply.vue';

export default {
  components: { 
    DeleteReplies 
    },
  name: "replies-component",
  data() {
    return {
      repliesByPost: [],
      newReply: {
        userId: this.$store.state.user.id,
        postId: this.$route.params.id,
        body: "",
      },
    };
  },
  methods: {
    submitReply() {
      replyService.createReply(this.newReply).then((response) => {
        if (response.status === 201) {
          this.$router.push({
            name: "postAndReplies",
            params: { id: this.newReply.postId },
          });
        }
      });
    },
    removeReply(reply) {
      this.repliesByPost = this.repliesByPost.filter(r => r.commentId !== reply.commentId);
    }
  },
  created() {
    replyService.getPostReplies(this.$route.params.id).then((response) => {
      this.repliesByPost = response.data;
    });
  },
};
</script>

<style scoped>
.new-reply-form {
  display: flex;
  flex-direction: column;
}
</style>
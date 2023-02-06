<template>
  <button v-on:click="deleteReply(postId, commentId)">Delete</button>
</template>

<script>
import replyService from "../services/ReplyService";

export default {
  name: "DeleteReply",
  props: {
    reply: Object
  },
  methods: {
    deleteReply(reply) {
  if (confirm("Are you sure you want to delete this reply?")) {
    const commentId = reply.commentId;
    const postId = reply.postId;
    replyService.deleteReply(postId, commentId)
    .then((response) => {
      if (response.status === 204) {
        this.$emit("replyDeleted", { commentId });
      }
    });
  }
}

  }
};
</script>
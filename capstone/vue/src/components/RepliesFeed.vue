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
  #replies-component {
    background-color: #f7f7f7;
    padding: 1rem;
    border-radius: 2px;
  }
  .create-reply {
    background-color: #fff;
    padding: 1rem;
    border-radius: 2px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.06);
    margin-bottom: 1rem;
  }
 .create-reply h3 {
    margin-bottom: 0.5rem;
  }
  .create-reply textarea {
    width: 100%;
    min-height: 4rem;
    padding: 0.5rem;
    border-radius: 2px;
    border: 1px solid #dcdce1;
    font-size: 1rem;
  }
 .create-reply .actions {
    display: flex;
    justify-content: flex-end;
    margin-top: 0.5rem;
  }
  .create-reply .actions button {
    background-color: #23468A;
    color: #fff;
    padding: 0.5rem 1rem;
    border-radius: 2px;
    border: none;
    font-size: 1rem;
    cursor: pointer;
  }
  .replies-feed {
    background-color: #fff;
    padding: 1rem;
    border-radius: 2px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.06);
    margin-bottom: 1rem;
  }
  .replies-feed h4 {
    margin-bottom: 0.5rem;
    font-size: 1.0rem;
  }
.replies-feed p {
    font-size: 1rem;
    line-height: 1.5;
  }

</style>
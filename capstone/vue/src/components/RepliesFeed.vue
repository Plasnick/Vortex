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
        <div class="status-message error" v-show="errorMsgSubmit !== ''">
        {{ errorMsgSubmit }}
      </div>
        <button v-on:click="submitReply()">Submit</button>
      </div>
    </form>

    <div
      class="replies-feed"
      v-for="reply in repliesByPost"
      v-bind:key="reply.commentId"
    >
      <h4>User: {{ reply.username }} Posted On: {{ reply.postedAt }}</h4>

      <p>{{ reply.body }}</p>
    
      <div class="status-message error" v-show="errorMsgDelete !== ''">
        {{ errorMsgDelete }}
      </div>
      <button v-show="isModerator" v-on:click="deleteReply(reply)">
        Delete Reply
      </button>
    </div>
  </div>
</template>

<script>
import replyService from "../services/ReplyService";
import moderatorsService from "../services/ModeratorsService";
import postsService from "../services/PostsService";


export default {
  components: {
   
  },
  name: "replies-component",
  data() {
    return {
      repliesByPost: [],
      post: [
        {
          forumId: null,
        },
      ],
      newReply: {
        userId: this.$store.state.user.id,
        postId: this.$route.params.id,
        body: "",
      },
      errorMsgDelete: "",
      errorMsgSubmit: "",
    };
  },
  computed: {
    isModerator() {
      if (this.$store.state.token == "") {
        return false;
      }
      if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
        return true;
      }
      for (let i = 0; i < this.$store.state.forumsModerated.length; i++) {
        let currentObj = this.$store.state.forumsModerated[i];
        if (currentObj.forumId === this.post[0].forumId) {
          return true;
        }
      }
      return false;
    },
  },
  methods: {
    submitReply() {
      if (this.$store.state.token == "") {
        this.$router.push({ name: "login" });
      }
      replyService.createReply(this.newReply).then((response) => {
        if (response.status === 201) {
          this.$router.push({
            name: "postAndReplies",
            params: { id: this.newReply.postId },
          });
        }
      }).catch((error) => {
              if (error.response) {
            this.errorMsgSubmit =
              "Error submitting new reply. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsgSubmit =
              "Error submitting new reply. Server could not be reached.";
          } else {
            this.errorMsgSubmit =
              "Error submitting new reply. Request could not be created.";
          }
            })
    },
    deleteReply(reply) {
      replyService
        .deleteReply(reply.commentId)
        .then((response) => {
          if (response.status == 204) {
            this.repliesByPost = this.repliesByPost.filter(
              (r) => r.commentId !== reply.commentId
            );
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsgDelete =
              "Error deleting reply. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsgDelete =
              "Error deleting reply. Server could not be reached.";
          } else {
            this.errorMsgDelete =
              "Error deleting reply. Request could not be created.";
          }
        });
    },
  },

  created() {
    replyService.getPostReplies(this.$route.params.id).then((response) => {
      this.repliesByPost = response.data;
      this.repliesByPost.forEach((element) => {
        element.postedAt =
          element.postedAt.substring(0, 10) +
          " " +
          element.postedAt.substring(11, 16);
      });
    });
    if (this.$store.state.token != "") {
      moderatorsService
        .getForumsModerated(this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_FORUMS_MODERATED", response.data);
          }
        });
    }
    postsService.getPost(this.$route.params.id).then((response) => {
      this.post = response.data;
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
  font-family: "Cabin";
}
.create-reply .actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 0.5rem;
}
.create-reply .actions button {
  background-color: #23468a;
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
  font-size: 1rem;
}
.replies-feed p {
  font-size: 1rem;
  line-height: 1.5;
}
</style>
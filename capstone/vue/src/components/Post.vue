<template>
  <div class="post-component">
    <div class="post-details">
      <h2>{{ post.title }}</h2>

      <span v-if="!hasInteracted">
        <button v-on:click="upVote">Up Vote ({{ post.upVotes }})</button>
        <button v-on:click="downVote">Down Vote ({{ post.downVotes }})</button>
      </span>
      <span v-else>
        <p>Up Votes: {{ post.upVotes }} | Down Votes: {{ post.downVotes }}</p>
      </span>
      <h3>User: {{ post.userId }} Posted On: {{ post.postedAt }}</h3>
      <button @click="deletePost">Delete Post</button>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>{{ post.body }}</p>
    </div>
  </div>
</template>

<script>
import postsService from "../services/PostsService";
import interactionsService from "../services/InteractionsService";
export default {
  name: "post-component",
  props: ["post"],
  data() {
    return {
      interaction: {
        userId: this.$store.state.user.id,
        postId: this.post.postId,
      },
    };
  },
  computed: {
    hasInteracted() {
      //  if(this.$store.state.interactions.includes(this.interaction)){
      //    return true;
      //  }
      //  return false;

      let interactionFlag = false;

      for (let i = 0; i < this.$store.state.interactions.length; i++) {
        let currentObj = this.$store.state.interactions[i];
        if (
          currentObj.userId == this.post.userId &&
          currentObj.postId == this.post.postId
        ) {
          return true;
        }
      }

      return interactionFlag;
    },
  },
  methods: {
    data() {
      return {
        showModal: false,
      };
    },
    upVote() {
      const index = this.$store.state.posts.findIndex(
        (element) => element.postId === this.post.postId
      );
      this.$store.state.posts[index].upVotes++;
      postsService
        .updatePost(this.post.postId, this.$store.state.posts[index])
        .then((response) => {
          if (response.status === 200) {
            console.log("it worked");
          }
        });
      this.$store.commit("ADD_INTERACTION", this.interaction);
      interactionsService.addInteraction(this.interaction);
    },
    downVote() {
      const index = this.$store.state.posts.findIndex(
        (element) => element.postId === this.post.postId
      );
      this.$store.state.posts[index].downVotes++;
      postsService
        .updatePost(this.post.postId, this.$store.state.posts[index])
        .then((response) => {
          if (response.status === 200) {
            console.log("it worked");
          }
        });
      this.$store.commit("ADD_INTERACTION", this.interaction);
      interactionsService.addInteraction(this.interaction);
    },
    deletePost() {
      postsService.deletePost(this.post.postId).then((response) => {
        if (response.status === 204) {
          this.$emit("post-deleted", this.post.postId);
        } else {
          console.error("Unable to delete post.");
        }
      });
    },
    confirmDelete() {
      this.showModal = true;
    },
  },
};
</script>

<style>
</style>
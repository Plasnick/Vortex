<template>
  <div>
    <button v-on:click="showModal = true">Delete Post</button>
    <modal v-if="showModal" @close="showModal = false">
      <template v-slot:header>
        Are you sure you want to delete this post?
      </template>
      <template v-slot:body>
        <p>This action cannot be undone.</p>
      </template>
      <template v-slot:footer>
        <button @click="confirmDelete">Delete Post</button>
        <button @click="showModal = false">Cancel</button>
      </template>
    </modal>
  </div>
</template>

<script>
import postsService from "../services/PostsService";

export default {
  props: {
    postId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      showModal: false
    };
  },
  methods: {
    deletePost() {
      postsService.deletePost(this.postId).then(response => {
        if (response.status === 204) {
          this.$emit("post-deleted", this.postId);
        } else {
          console.error("Unable to delete post.");
        }
        this.showModal = false;
      });
    }
  }
};
</script>

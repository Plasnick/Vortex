<template>
  <div class="post-component">
    <div class="post-details">
      <h2>{{ post.title }}</h2>
      <button v-on:click="upVote">Up Vote ({{ post.upVotes }})</button>
      <button v-on:click="downVote">Down Vote ({{ post.downVotes }})</button>
      <h3>User: {{ post.userId }} Posted On: {{ post.postedAt }}</h3>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>{{ post.body }}</p>
    </div>
  </div>
</template>

<script>
 import postsService from "../services/PostsService"; 

export default {
  name: "post-component",
     props: ["post"],
     methods: {
       upVote() {
         const index = this.$store.state.posts.findIndex((element) => (element.postId === this.post.postId));
         this.$store.state.posts[index].upVotes++
         postsService.updatePost(this.post.postId, this.$store.state.posts[index]).then((response) => {
           if (response.status === 200) {
             console.log("it worked");
           }
         });
       },
       downVote() {
         const index = this.$store.state.posts.findIndex((element) => (element.postId === this.post.postId));
         this.$store.state.posts[index].downVotes++
         postsService.updatePost(this.post.postId, this.$store.state.posts[index]).then((response) => {
           if (response.status === 200) {
             console.log("it worked");
           }
         });
       },
     },
};
</script>

<style>
</style>
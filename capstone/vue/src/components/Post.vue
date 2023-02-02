<template>
  <div class="post-component">
    <div class="post-details">
      <h2>{{ post.title }}</h2>
      <h3>User: {{ post.userId }} Posted On: {{ post.postedAt }}</h3>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>{{ post.body }}</p>
    </div>
  </div>
</template>

<script>
// import postsService from "../services/PostsService";
export default {
  name: "post-component",
  //   props: ["post"],
  //   methods: {
  //     upVote() {
  //       this.post.upVotes++;
  //       postsService.updatePost(this.post).then((response) => {
  //         if (response.status === 200) {
  //           console.log("it worked");
  //         }
  //       });
  //     },
  //     downVote() {
  //       this.post.downVotes++;
  //       postsService.updatePost(this.post).then((response) => {
  //         if (response.status === 200) {
  //           console.log("it worked");
  //         }
  //       });
  //     },
  //   },
};
</script>

<style>
</style>
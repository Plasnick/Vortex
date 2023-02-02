<template>
  <div id="main-forum-feed">
    <div class="post" v-for="post in postsByForum" v-bind:key="post.postId">
      <h3>{{ post.title }}</h3>
      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>{{ post.postedAt }}</p>
      <p>{{ post.body }}</p>
    </div>
  </div>
</template>

<script>
import PostsService from "../services/PostsService";
export default {
  name: "forum-main-feed",
  data() {
    return {
      postsByForum: [],
    };
  },
  created() {
    PostsService.getForumPosts(this.$route.params.id).then((response) => {
      this.postsByForum = response.data;
    });
  },
};
</script>

<style>
</style>
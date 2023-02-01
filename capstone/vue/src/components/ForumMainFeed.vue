<template>
  <div id="main-forum-feed">
    <div class="post" v-for="post in postsByForum" v-bind:key="post.postId">
      <router-link
        v-bind:to="{ name: 'postAndReplies', params: { id: post.id } }"
      >
        <h3>{{ post.title }}</h3>
      </router-link>
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
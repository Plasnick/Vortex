<template>
  <div class="post-and-replies">
    <post />
    <replies-feed />
  </div>
</template>

<script>
import RepliesFeed from "../components/RepliesFeed.vue";
import Post from "../components/Post.vue";

export default {
  components: { RepliesFeed, Post },
  name: "post-and-replies",
};
</script>

<style>
</style>
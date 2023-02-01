<template>
  <div id="main-forum-feed">
    <h3>{{ forum.forumName }}</h3>
    <ul id="post-list">
      <li></li>
    </ul>
  </div>
</template>

<script>
import ForumsService from "../services/ForumsService";
export default {
  data() {
    return {
      mostRecentForumPosts: [],
    };
  },
  created() {
    ForumsService.getForumPosts().then((response) => {
      this.mostRecentForumPosts = response.data;
    });
  },
};
</script>

<style>
</style>
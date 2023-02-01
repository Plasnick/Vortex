<template>
  <div class="forum-details">
    <h2>{{ forum.forumName }}</h2>
    <h3>Description:</h3>
    <p>{{ forum.description }}</p>
    <h3>Rules:</h3>
    <p>{{ forum.rules }}</p>
    <forum-main-feed />
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";

import ForumMainFeed from "../components/ForumMainFeed.vue";
export default {
  components: {
    ForumMainFeed,
  },
  data() {
    return {
      forum: {},
      moderators: [],
    };
  },
  created() {
    forumsService.getForum(this.$route.params.id).then((response) => {
      this.forum = response.data;
    });
  },
};
</script>

<style>
</style>
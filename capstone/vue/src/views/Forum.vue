<template>
  <div class="forum-details">
    <h2>{{ forum.forumName }}</h2>
    <favorite-checkbox v-bind:forum="forum" />
    <h3>Description:</h3>
    <p>{{ forum.description }}</p>
    <h3>Rules:</h3>
    <p>{{ forum.rules }}</p>
    <router-link v-bind:to="{ name: 'newPost', params: { id: forum.id } }"
      >CREATE A POST</router-link
    >
    <forum-main-feed />
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";

import ForumMainFeed from "../components/ForumMainFeed.vue";
import FavoriteCheckbox from "../components/FavoriteCheckbox.vue";
export default {
  components: {
    ForumMainFeed,
    FavoriteCheckbox,
  },
  data() {
    return {
      forum: [],
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
<template>
  <div class="favorite-forums">
    <ul id="favorite-forums-list">
      <h3>Your favorite Forums:</h3>
      <li v-for="forum in favoriteForums" v-bind:key="forum.id">
        <router-link v-bind:to="{ name: 'forum', params: { id: forum.id } }">{{
          forum.forumName
        }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";

export default {
  name: "favorite-forums-list",
  components: {},
  data() {
    return {
      favoriteForums: [],
    };
  },
  methods: {},
  created() {
    if (this.$store.state.token != "") {
      forumsService
        .favoriteForumsByUserId(this.$store.state.user.id)
        .then((response) => {
          this.favoriteForums = response.data;
        });
    }
  },
};
</script>

<style>
</style>

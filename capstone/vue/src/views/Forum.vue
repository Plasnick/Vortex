<template>
  <div class="forum-details">
    <h2>{{ forum.forumName }}</h2>
    <favorite-checkbox v-bind:forum="forum" />
    <h3>Moderated by:</h3>
    <ul>
      <li
        v-for="moderator in $store.state.moderatorsForForum"
        v-bind:key="moderator.moderatorId"
      >
        {{ moderator.username }}
      </li>
    </ul>
    <span v-show="isModerator">
      <p>Add a moderator?</p>
      <add-moderator />
    </span>
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
import moderatorsService from "../services/ModeratorsService";
import AddModerator from "../components/AddModerator.vue";

export default {
  components: {
    ForumMainFeed,
    FavoriteCheckbox,
    AddModerator,
  },
  data() {
    return {
      forum: [],
      moderators: [],
    };
  },
  computed: {
    isModerator(){
      if(this.$store.state.token == ''){
        return false;
      }
      if(this.$store.state.user.authorities[0].name == "ROLE_ADMIN"){
        return true;
      }
      for (let i = 0; i < this.$store.state.moderatorsForForum.length; i++) {
        let currentObj = this.$store.state.moderatorsForForum[i];
        if (currentObj.moderatorId === this.$store.state.user.id) {
          return true;
        }
      }
      return false;
    },
  },
  created() {
    forumsService.getForum(this.$route.params.id).then((response) => {
      this.forum = response.data;
    });

    moderatorsService
      .getModeratorsForForum(this.$route.params.id)
      .then((response) => {
        this.$store.commit("SET_MODERATORS_FOR_FORUM", response.data);
      });

    moderatorsService;
  },
};
</script>

<style>
</style>

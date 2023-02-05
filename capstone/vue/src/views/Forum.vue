<template>
  <div class="forum-details">
    
    <h2>{{ forum.forumName }}</h2>
    <h3>Moderated by:</h3>
    <ul>
      <li v-for="moderator in $store.state.moderatorsForForum" v-bind:key="moderator.moderatorId">
        {{moderator.username}}
      </li>
    </ul>
    <h3>Description:</h3>
    <p>{{ forum.description }}</p>
    <h3>Rules:</h3>
    <p>{{ forum.rules }}</p>
    <router-link v-bind:to="{name: 'newPost', params:{id: forum.id}}">CREATE A POST</router-link>
    <forum-main-feed />
    
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";

import ForumMainFeed from "../components/ForumMainFeed.vue";
import moderatorsService from "../services/ModeratorsService";

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

    moderatorsService.getModeratorsForForum(this.$route.params.id).then((response)=>{
      this.$store.commit("SET_MODERATORS_FOR_FORUM", response.data)
    })

    moderatorsService
  },
};
</script>

<style>
</style>
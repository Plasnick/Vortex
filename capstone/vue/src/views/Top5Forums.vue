<template>
  <div>
    Most recently Active Forums:

    <!-- <div class="recent5List" v-for="entry in mostRecentForums" v-bind:key> -->
    <p>{{ mostRecentForums[0].forumName }}</p>
    <p>{{ mostRecentForums[1].forumName }}</p>
    <p>{{ mostRecentForums[2].forumName }}</p>
    <p>{{ mostRecentForums[3].forumName }}</p>
    <p>{{ mostRecentForums[4].forumName }}</p>
    <!-- </div> -->
  </div>
</template>

<script>
import ShowRecent5 from "../services/ForumsService";

export default {
  data() {
    return { mostRecentForums: [] };
  },

  created() {
    ShowRecent5.displayTop5().then((response) => {
      this.mostRecentForums = response.data;
    });
  },
};
</script>

<style>
/* html {
    color: white;
    background-color: black;
} */
</style> 
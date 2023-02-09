<template>
  <div class="results">
    these are the search results

    <search-results />
  </div>
</template>

<script>
import SearchResults from "../components/SearchResults.vue";

export default {
  components: {
    SearchResults,
  },
};
</script>

<style>
.results{
  margin-top: 100px;
}
</style>
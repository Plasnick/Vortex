<template>
  <div>
<<<<<<< HEAD
    <div class="container">
=======
>>>>>>> main
    <form @submit.prevent="search">
      <input type="text" v-model="query" placeholder="Search Vortex For" />
      <div class="select-bar">
      <select v-model="selectedSearchOption" class="dropdown-input">
        <option
          v-for="(options, key) in searchOptions"
          :key="key"
          :value="options">
          {{ options.text }}
        </option>
      </select>
      </div>
      <button type="submit">Search</button>
    </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-bar",
  data() {
    return {
      query: "",
      selectedSearchOption: { text: "Posts", value: "searchForPosts" },
      searchOptions: [
        { text: "Forums", value: "searchForForums" },
        { text: "Posts", value: "searchForPosts" },
      ],
    };
  },

  methods: {
    search() {
      if (this.$route.name === "search") {
        this.$router.replace({
          params: {
            query: this.query,
            option: this.selectedSearchOption.value,
          }
        });
        location.reload();
      } else {
        this.$router.push({
          name: "search",
          params: {
            query: this.query,
            option: this.selectedSearchOption.value,
          },
        });
      }
    },
  },
};
</script>

<style scoped>

input[type="text"]{
    border: none;
    outline: none;
    background-color: #ececec;
    margin-right: 130px;
    margin-bottom: 20px;
    flex-direction: row;
}
input::placeholder {
  color: #23468A;
  display: flex;
  flex-direction: row;
  outline-style: none;
  background-color: #EDEDED;
  }
    .select-bar {
    background-color: #EDEDED;
  }

</style>
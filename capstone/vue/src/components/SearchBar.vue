<template>
  <div>
    test text
    <form @submit.prevent="search">
      <input type="text" v-model="query" placeholder="Search Vortex For" />
      <select v-model="selectedSearchOption" class="dropdown-input">
        <option
          v-for="(options, key) in searchOptions"
          :key="key"
          :value="options"
        >
          {{ options.text }}
        </option>
      </select>
      <button type="submit">Search</button>
    </form>
  </div>
</template>

<script>

export default {
  name: "search-bar",
  data() {
    return {
      query: "",
      selectedSearchOption: {text: 'Posts', value: 'searchForPosts'},
      searchOptions: [
        {text: 'Forums', value: 'searchForForums'}, 
        {text: 'Posts', value: 'searchForPosts'}
      ],
    };
  },

  methods: {
    search() {
      this.$router.push({
        name: 'search',
        params: { query: this.query, option: this.selectedSearchOption.value}
      })
    }

  }
};
</script>

<style scoped>
.search-forum{
    display: flex;
    flex-direction: column;
    border-radius: 20px;
    border: 1px solid lightgrey;
    padding: 10px 20px;
    outline-style: none;
    background-color: #EDEDED;
    width:60%;    
}
input[type="text"]{
    border: none;
    outline: none;
    background-color: #EDEDED;
}
input::placeholder {
  color: #23468A;
}
.suggestions {
  background-color: rgb(224, 219, 219);
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.616);
  padding: 8px;
}
</style>
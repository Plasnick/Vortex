

<template>
  <div>
    <div class="container">
    <form @submit.prevent="search">
      <div class="input-and-select">
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
      </div>
      <a href="search" type="submit">Search</a>
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

.container{
  display: flex;
  flex-direction: row;
  align-items: center;
}

.input-and-select{
  display: flex;
  flex-direction: row;
  align-items: center;
}
input[type="text"]{
    border: none;
    outline: none;
    background-color: #ececec;
    margin-bottom: 20px;
    flex-grow: 1;
    margin-top: 70px;
}
input::placeholder {
  color: #23468A;
  display: flex;
  flex-direction: row;
  outline-style: none;
  background-color: #EDEDED;
  }

.dropdown-input{
  background-color: #EDEDED;
  padding: 0px;
  border: none;
  outline: none;
  margin-top: 48px;
  color: #23468A;
  margin-right: 140px;
  
}

a{
background-color: #EDEDED;
margin-left: 300px;
padding: 10px 20px;
color: #23468A;
margin-bottom: 10px;
width: 5px;
}
</style>























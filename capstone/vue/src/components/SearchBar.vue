<template>
  <div>
    <div class="container">
    <form @submit.prevent="search">
      <div class="input-and-select">
        <input type="text" v-model="query" placeholder="Get sucked into..." />
        <button href="search" type="submit" class="submit-search">Search</button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <select v-model="selectedSearchOption" class="dropdown-input">
            <option
              v-for="(options, key) in searchOptions"
              :key="key"
              :value="options">
              {{ options.text }}
            </option>
          </select>
        
      </div>
      
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
  padding-bottom: 50px;
}

input{
  padding-right: 8vw;
  margin-left: 0vw;
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
    /* flex-grow: 1; */
    margin-top: 70px;
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

.dropdown-input{
  background-color: #EDEDED;
  padding: 0px;
  border: none;
  outline: none;
  margin-top: 48px;
  color: #23468A;
  
  
}

.submit-search{
  border-radius: 13px;
  width: 70px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer;
  background-color: #23468A;
  color: #f3f3f3;
  margin-top: 50px;
  margin-left: 0vw;
}
.submit-search:hover {
  background-color: #1483d6;
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

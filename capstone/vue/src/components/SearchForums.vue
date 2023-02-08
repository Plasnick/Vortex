<template>
  <div class="search-forum">
      <input type="text" placeholder="Search Vortex" v-model="searchWord">
      <div v-if="searchWord" class="suggestions">
        <div v-for="forum in filteredList" v-bind:key="forum.id">
          <router-link v-bind:to="{name: 'forum', params: {id: forum.id}}"> {{ forum.forumName }}  </router-link>
        </div>
      </div>
  </div>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    data(){
        return {
            searchWord: "",
            allForums: []
        }
    },
    computed : {
        filteredList(){
            return this.allForums.filter((forum)=>{
                if(forum.forumName.toLowerCase().includes(this.searchWord.toLowerCase())){
                    return forum;
                }
            })
                
        }
    },
    created(){
        forumsService.getAllForums().then((response) => {
            this.allForums = response.data;
        });
    }

}
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

</style>

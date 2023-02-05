<template>
  <div id="main-forum-feed">
    <div v-for="post in postsByForum" v-bind:key="post.postId">
      <post  v-bind:post="post" />
      <button v-show="isModerator" v-on:click="addModerator(post)">Add this user as a moderator</button>
      <p v-show="errorMessage">This user is already a moderator</p>
    </div>
    
    
  </div> 

</template>

<script>
import PostsService from "../services/PostsService";
import Post from "../components/Post.vue"
import interactionsService from "../services/InteractionsService"
import moderatorsService from "../services/ModeratorsService"
export default {
  name: "forum-main-feed",
  components: {
    Post
  },
  data() {
    return {
      postsByForum: [],
      errorMessage: false
    };
  },
  computed:{
    isModerator(){
      for(let i=0;i<this.$store.state.moderatorsForForum.length;i++){
        let currentObj = this.$store.state.moderatorsForForum[i];
        if(currentObj.moderatorId == this.$store.state.user.id && currentObj.forumId==this.$route.params.id){
          return true
        }
      }
      return false
    }
  },
  methods: {
    addModerator(post){
      let isInList = false;
      for(let i=0;i<this.$store.state.moderatorsForForum.length;i++){
        let currentObj = this.$store.state.moderatorsForForum[i];
        if(currentObj.moderatorId == post.userId && currentObj.forumId==this.$route.params.id){
          isInList = true;
        }
      }
      if(isInList){
        this.errorMessage = true;
      }else{
        let moderator = {
          moderatorId : post.userId,
          forumId : this.$route.params.id
        }
        moderatorsService.addModerator(moderator).then((response)=>{
          if (response.status==201){
            this.$store.commit("ADD_MODERATOR", moderator)
          }
        })
        
      }
    }
  },
  created() {
    PostsService.getForumPosts(this.$route.params.id).then((response) => {
      this.postsByForum = response.data;
      this.$store.commit("SET_POSTS", response.data)
    });
    interactionsService.getInteractions(this.$store.state.user.id).then((response) =>{
      this.$store.commit("SET_INTERACTIONS", response.data)
    })
  },
};
</script>

<style>
</style>
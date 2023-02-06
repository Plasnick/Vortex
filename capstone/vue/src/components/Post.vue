<template>
  <div class="post-component">
    <header class="header">
      <router-link class="title-link" v-bind:to="{name:'postAndReplies', params:{id: post.postId}}">
      <h2>{{ post.title }}</h2>
      </router-link>
      <h3>from <router-link class="forum-link" v-bind:to="{name:'forum', params:{id:post.forumId}}">{{forum.forumName}}</router-link></h3>
    </header>
    <div class="post-content">
      
      <p>{{ post.body }}</p>
       <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>by {{ post.userId }} posted on {{ post.postedAt }}</p>
      
    </div>
      <div v-if="!hasInteracted">
        <i class="fa-solid fa-arrow-up" v-on:click="upVote"></i>
        <span> {{ post.upVotes }} </span>
        <i class="fa-solid fa-arrow-down" v-on:click="downVote"></i>
        <span> {{ post.downVotes }} </span>
      </div>
      <span v-else>
        <i class="fa-solid fa-arrow-up" ></i>
        <span> {{ post.upVotes }} </span>
        <i class="fa-solid fa-arrow-down" ></i>
        <span> {{ post.downVotes }} We got your vote!</span>
      </span>
      <h3>User: {{ post.username }} Posted On: {{ post.postedAt }}</h3>
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->
     
      
      
    
  </div>
</template>

<script>
import postsService from "../services/PostsService";
import interactionsService from "../services/InteractionsService";
import forumsService from "../services/ForumsService"
export default {
  name: "post-component",
     props: ["post"],
     data(){
        return{
         interaction: {
           userId: this.$store.state.user.id,
           postId: this.post.postId
         },
         forum: {}
       }
     },
     computed: {

       hasInteracted(){
     
        let interactionFlag = false;
        for (let i = 0; i < this.$store.state.interactions.length; i++) {
          let currentObj = this.$store.state.interactions[i];
          if ( currentObj.userId == this.$store.state.user.id && currentObj.postId == this.post.postId) {
            return true;
          }
        
      }
      return interactionFlag;
      }
        
       },
       

     methods: {
        upVote() {
          if(this.$store.state.token == ''){
            this.$router.push('/login')
          }else{
         const index = this.$store.state.posts.findIndex((element) => (element.postId === this.post.postId));
         this.$store.state.posts[index].upVotes++
         postsService.updatePost(this.post.postId, this.$store.state.posts[index]).then((response) => {
           if (response.status === 200) {
             console.log("it worked");
           }
         });
         this.$store.commit("ADD_INTERACTION", this.interaction);
         interactionsService.addInteraction(this.interaction);
          }

       },
       downVote() {
         if(this.$store.state.token == ''){
           this.$router.push('/login')
         }else{
         const index = this.$store.state.posts.findIndex((element) => (element.postId === this.post.postId));
         this.$store.state.posts[index].downVotes++
         postsService.updatePost(this.post.postId, this.$store.state.posts[index]).then((response) => {
           if (response.status === 200) {
             console.log("it worked");
           }
         });
         this.$store.commit("ADD_INTERACTION", this.interaction);
         interactionsService.addInteraction(this.interaction);
         }
       },
  },
  created(){
    forumsService.getForum(this.post.forumId).then((response)=>{
      this.forum = response.data;
    })
  }
     
    
};
</script>

<style scoped>
.post-component{
  display: flex;
  flex-direction: column;
  border: 1px solid;
  padding-left: 10px;
  margin-bottom: 10px;
  border-radius: 2%;
  padding-bottom: 10px;
  
}
.header{
  color: #23468a;
  font-size: 12pt;
  
}

.title-link{
  color: #23468a;
  font-size: 12pt;
  text-decoration: none;
}
.title-link:hover{
  text-decoration: underline;
}

.forum-link{
  color: #23468a;
  text-decoration: none;
}
.forum-link:hover{
  text-decoration: underline;
}
img{
  height: 20em;
}
i{
  color: #23468a;
}
i:hover{
  cursor: pointer;
}

</style>
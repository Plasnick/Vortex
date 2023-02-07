<template>
  <div class="post-component">
    <header class="header">
      <h3>v/<router-link class="forum-link" v-bind:to="{name:'forum', params:{id:post.forumId}}">{{forum.forumName}}</router-link></h3>
      <router-link class="title-link" v-bind:to="{name:'postAndReplies', params:{id: post.postId}}">
      <h2>{{ post.title }}</h2>
      </router-link>
      
    </header>
    <div class="post-content">
      
      <p>{{ post.body }}</p>
       <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      
      
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
      <h3>by {{ post.username }} on {{ date }}</h3>
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
         forum: {},
         date: null
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
    this.date = this.post.postedAt.substring(0,10) + " " + this.post.postedAt.substring(11, 16)
    console.log(this.date)
  }
     
    
};
</script>

<style scoped>
.post-component {
  background-color: #F8F8F8;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  box-shadow: 0 1px 2px rgba(0,0,0,.05);
  display: flex;
  flex-direction: column;
  width: 50%;
  margin: 20px auto;
  padding: 10px;
}
 .header {
  display: flex;
  flex-direction: column;
}
 .title-link {
  color: #333;
  text-decoration: none;
}
.title-link h2 {
  color: #545454;
  font-size: 20px;
  margin: 0;
}
.forum-link {
  color: #23468A;
  font-size: 14px;
  text-decoration: none;
}
.forum-link:hover{
  text-decoration: underline;
  }
.title-link:hover{
  text-decoration: underline;
}

.post-content {
  margin-top: 15px;
}
.post-content p {
  color: #333;
  font-size: 14px;
  margin: 0;
}

.post-content img {
  max-width: 100%;
}

.fa-solid {
  cursor: pointer;
  margin-right: 10px;
}
.fa-arrow-up, .fa-arrow-down {
  color: #23468A;
}

.fa-arrow-up:hover, .fa-arrow-down:hover {
  color: #1096c8;
}

h3{
color: #23468A;
}


</style>
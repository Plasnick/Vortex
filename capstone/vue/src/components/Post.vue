<template>
  <div class="post-component">

    <div class="sidebar">
    <div class="votes">

      <div v-if="!hasInteracted">
        <i class="fa-solid fa-arrow-up" v-on:click="upVote"></i>
        <span> {{ post.upVotes }} </span>
        <i class="fa-solid fa-arrow-down" v-on:click="downVote"></i>
        <span> {{ post.downVotes }} </span>
      </div>
      <span v-else>
        <i class="fa-solid fa-arrow-up"></i>
        <span> {{ post.upVotes }} </span>
        <i class="fa-solid fa-arrow-down"></i>
        <span> {{ post.downVotes }} </span> 
        <button @click="deleteInteraction">
        <i class="fa-solid fa-rotate-left"></i>
      </button>
      </span>

    </div>
    </div>

    <header class="header">
      <h3>v/<router-link class="forum-link" v-bind:to="{name:'forum', params:{id:post.forumId}}">{{forum.forumName}}</router-link></h3>
      <h3>by {{ post.username }} on {{ date }}</h3>
      <router-link class="title-link" v-bind:to="{name:'postAndReplies', params:{id: post.postId}}">
        <h2>{{ post.title }}</h2>
      </router-link>
    <div class="post-content">
      <p>{{ post.body }}</p>
      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
    </div>
    </header>

  </div>
</template>    

 

<script>
import postsService from "../services/PostsService";
import interactionsService from "../services/InteractionsService";
import forumsService from "../services/ForumsService";
export default {
  name: "post-component",
  props: ["post"],
  data() {
    return {
      interaction: {
        userId: this.$store.state.user.id,
        postId: this.post.postId,
        upOrDown: false,
      },
      forum: {},
      date: null,
    };
  },
  computed: {
    hasInteracted() {
      let interactionFlag = false;
      for (let i = 0; i < this.$store.state.interactions.length; i++) {
        let currentObj = this.$store.state.interactions[i];
        if (
          currentObj.userId == this.$store.state.user.id &&
          currentObj.postId == this.post.postId
        ) {
          return true;
        }
      }
      return interactionFlag;
    },
  },

  methods: {
    upVote() {
      if (this.$store.state.token == "") {
        this.$router.push("/login");
      } else {
        this.interaction.upOrDown = true;
        const index = this.$store.state.posts.findIndex(
          (element) => element.postId === this.post.postId
        );
        this.$store.state.posts[index].upVotes++;
        postsService
          .updatePost(this.post.postId, this.$store.state.posts[index])
          .then((response) => {
            if (response.status === 200) {
              console.log("it worked");
            }
          });
        this.$store.commit("ADD_INTERACTION", this.interaction);
        interactionsService.addInteraction(this.interaction);
      }
    },
    downVote() {
      if (this.$store.state.token == "") {
        this.$router.push("/login");
      } else {
        this.interaction.upOrDown = false;
        const index = this.$store.state.posts.findIndex(
          (element) => element.postId === this.post.postId
        );
        this.$store.state.posts[index].downVotes++;
        postsService
          .updatePost(this.post.postId, this.$store.state.posts[index])
          .then((response) => {
            if (response.status === 200) {
              console.log("it worked");
            }
          });
        this.$store.commit("ADD_INTERACTION", this.interaction);
        interactionsService.addInteraction(this.interaction);
      }
    },
    deleteInteraction() {
      const index = this.$store.state.posts.findIndex(
        (element) => element.postId === this.post.postId
      );
      if (this.interaction.upOrDown == true) {
        this.$store.state.posts[index].upVotes--;
      }
      if (this.interaction.upOrDown == false) {
        this.$store.state.posts[index].downVotes--;
      }
      postsService
        .updatePost(this.post.postId, this.$store.state.posts[index])
        .then((response) => {
          if (response.status === 200) {
            console.log("it worked");
          }
        });
      this.$store.commit("DELETE_INTERACTION", this.interaction);
      interactionsService.deleteInteraction(this.interaction);
    },
  },
  created() {
    forumsService.getForum(this.post.forumId).then((response) => {
      this.forum = response.data;
    });
    this.date =
      this.post.postedAt.substring(0, 10) +
      " " +
      this.post.postedAt.substring(11, 16);
    console.log(this.date);
  },
};
</script>

<style scoped>
.post-component {
  display: flex;
  border: 1px solid #e5e5e5;
  background-color: #f8f8f8;
  border-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
  width: 100%;
  margin: 20px auto;
  padding: 10px;  
  flex-direction: row;
  margin-top: 130px;
}
.post-content img {
  max-width: 100%;
}
.sidebar {
  width: 5%;
  text-align: center;
}
.votes {
  padding: 20px;
}
.header {
  padding: 20px;
  width: 100%;
}
.post-content {
  padding: 20px;
}
.post-content p {
  color: #333;
  font-size: 14px;
  margin: 0;
}

.fa-solid {
  font-size: 25px;
  cursor: pointer;
  margin-right: 1px;
}
.fa-rotate-left {
  font-size: 20px;
  cursor: pointer;
}
.fa-arrow-up,
.fa-arrow-down {
  color: #23468a;
  margin-right: 100%;
}
.fa-arrow-up:hover,
.fa-arrow-down:hover, 
.fa-rotate-left:hover {
  color: #1096c8;
}
h3 {
  color: #23468a;
}
button {
  color: #23468a;
  border: none;
  cursor: pointer;
  margin-left: 0.2rem;
  font-size: 1rem;
  width: 25px;
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
  color: #23468a;
  font-size: 14px;
  text-decoration: none;

}
.title-link:hover {
  text-decoration: underline;
}

.forum-link:hover {
  text-decoration: underline;
}

</style>

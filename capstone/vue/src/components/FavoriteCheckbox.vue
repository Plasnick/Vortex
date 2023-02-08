<template>
  <div class="favorite-checkbox">
    <label class="favorite-checkbox__label">
      <input
        type="checkbox"
        v-model="favoriteForum"
        v-on:change="onFavoritedChange($event)"
        v-bind:checked="favoriteForum"
        class="favorite-checkbox__input"
      />
      Favorite forum
    </label>
  </div>
</template>

<script>
import favoriteService from "../services/FavoriteService";
export default {
  name: "favorite-checkbox",
  props: ["forum"],
  data() {
    return {
      favoriteForum: false,
      favorite: {
        userId: this.$store.state.user.id,
        favoriteId: this.$route.params.id,
      },
    };
  },
  computed: {},
  methods: {
    //need try catches
    // need response status for the deleted post

    onFavoritedChange(event) {
      if (this.$store.state.token == "") {
        this.$router.push("/login");
      } else {
        if (event.target.checked) {
          this.$store.commit("ADD_FAVORITE", this.favorite);
          favoriteService.addFavorite(this.favorite);
          this.favoriteForum = true;
        } else {
          this.$store.commit("DELETE_FAVORITE", this.favorite);
          favoriteService.deleteFavorite(this.favorite);
          this.favoriteForum = false;
        }
      }
    },
    checkFavoriteForum() {
      //do I need to account for if there isn't a favorite object?
      for (let i = 0; i < this.$store.state.favorites.length; i++) {
        let currentObj = this.$store.state.favorites[i];
        if (
          currentObj.userId == this.$store.state.user.id &&
          currentObj.favoriteId == this.$route.params.id
        ) {
          this.favoriteForum = true;
        }
      }
    },
  },
  created() {
    favoriteService.getFavorites(this.$store.state.user.id).then((response) => {
      this.favorites = response.data;
      this.$store.commit("SET_FAVORITES", response.data);
      this.checkFavoriteForum();
    });
  },
};
</script> 

<style>
 .favorite-checkbox {
    display: inline-block;
    font-size: 14px;
  }
  
  .favorite-checkbox__label {
    display: flex;
    align-items: center;
    cursor: pointer;
  }
  
  .favorite-checkbox__input {
    margin-right: 5px;
  }
</style>
<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div id="need-an-account" class="need-an-account">
      <router-link :to="{ name: 'register' }">Need an account?</router-link> 
      </div>
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";


export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>
#login {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }
    .form-signin {
    width: 360px;
    padding: 30px;
    border: 1px solid #ccc;
    box-shadow: 0 0 10px rgb(204, 204, 204);
    text-align: left;
  }

  .form-signin .h3 {
    margin-bottom: 20px;
    font-size: 25px;
  }
  .form-signin .alert {
    margin-bottom: 20px;
  }
.form-signin input[type="text"],
  .form-signin input[type="password"] {
    margin-bottom: 20px;
  }
.form-signin button[type="submit"] {
  width: 100%;
  background-color: #23468A;
  color: #EDEDED;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.form-signin button[type="submit"]:hover {
  background-color: #09A0D8;
}

#need-an-account {
  display: inline-block;
  float: right;
  margin-top: 10px;
  font-size: 16px;
  color: #09A0D8;
}


</style>
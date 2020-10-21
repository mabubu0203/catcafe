<template>
  <form v-on:submit.prevent="doLogin">
    <label>User ID</label>
    <input type="text" placeholder="customer id" v-model="user.userId"/>
    <label>Password</label>
    <input type="password" placeholder="password" v-model="user.password"/>
    <button type="submit">Sign In</button>
  </form>
</template>
<script>
export default {
  data() {
    return {
      user: {}
    };
  },
  methods: {
    doLogin() {
      this.axios.post(uri, this.user).then(response => {
        this.$store.dispatch("auth", {
          userId: response.data.userId,
          userToken: response.data.token
        });
        this.$router.push(this.$route.query.redirect);
      });
    }
  }
};
</script>
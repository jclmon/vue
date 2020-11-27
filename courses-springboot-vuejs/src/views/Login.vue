<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Username</label>
          <input
            type="text"
            class="form-control"
            name="username"
            v-model.trim='$v.username.$model'
          />
          <div
            class="alert alert-danger"
            role="alert"
            v-if="!$v.username.required && $v.username.$dirty"
          >Username is required.</div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            class="form-control"
            name="password"
            v-model.trim='$v.password.$model'
          />
          <div
            class="alert alert-danger"
            role="alert"
            v-if="!$v.password.required && $v.password.$dirty"
          >Password is required.</div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span class="spinner-border spinner-border-sm" v-show="loading"></span>
            <span>Login</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import UserService from "../services/user.service";
import { User } from "../models/user";
import { required } from 'vuelidate/lib/validators'
export default {
  name: "login",
  data() {
    return {
      username: '',
      password: '',
      loading: false
    };
  },
  validations: {
    username: {
      required
    },
    password: {
      required
    }
  },
  mounted() {
    if (UserService.currentUserValue) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      if (this.$v.$anyError) {
        this.loading = false;
        return;
      }
      console.log(this.username);
      console.log(this.password);
      let user = new User(this.username, this.password);
      UserService.login(user).then(
        data => {
          //You can get warning, if you don't use paremeters.
          console.log(data);
          this.$router.push("/profile");
        },
        error => {
          //You can get warning, if you don't use paremeters.
          console.log(error);
          this.loading = false;
          this.$store.dispatch("error", "Username or password is not valid.");
        }
      );
    }
  }
};
</script>
<style scoped>
label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  /* just in case there no content*/
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  /* shadows and rounded borders */
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
<script setup>
//  import LoginVue from './views/login.vue'
//  import LayoutVue from './views/layout.vue'

// import {ref,onMounted} from 'vue';
// export default{
//     name:'App',
//     setup(){
//         const items =ref([]);

//         onMounted(async()=>{
//             try{
//                 const response = await fetch('/user/getAll');
//                 const data = await response.json();
//                 items.value = data;

//             }catch(error){
//                 console.error('Error fetching item:',error);
//             }
//         });
//         return {
//             items;
//         };
//     };
// };
</script>

<template>
  <router-view></router-view>
  <!-- <LayoutVue/> -->
  <!-- <div>
    <h1>项目列表</h1>
    <ul>
        <li v-for="item in items" :key="item.id">
        {{ item.name }}</li>
    </ul>
</div> -->
</template>

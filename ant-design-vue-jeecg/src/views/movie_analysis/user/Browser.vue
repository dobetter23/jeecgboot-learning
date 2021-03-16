<template>
  <div>
    <div style="padding: 20px">
      <a-row :gutter="16">
        <a-col :span="6">
          <a-card title="李焕英" hoverable :bordered="false">
            <template #cover>
              <a href="https://www.baidu.com" @click="consolelog()">
                <img height="400px" alt="李焕英" src="../../../assets/moviecover/lihuanying.png" />
              </a>
            </template>
          </a-card>
        </a-col>
        <a-col :span="6">
          <a-card title="阿凡达" :bordered="false">
            <template #cover>
              <img height="400px" alt="example" src="../../../assets/moviecover/avatar.jpg" />
            </template>
          </a-card>
        </a-col>
        <a-col :span="6">
          <a-card title="唐人街探案3" :bordered="false">
            <template #cover>
              <img height="400px" alt="example" src="../../../assets/moviecover/tang3.jpg" />
            </template>
          </a-card>
        </a-col>
        <a-col :span="6">
          <a-layout>
            <!-- <a-layout-header style="background: #fff; padding: 0" /> -->
            <a-layout-content style="margin: 0 16px">
              <a-breadcrumb style="margin: 16px 0">
                <a-breadcrumb-item>User</a-breadcrumb-item>
                <a-breadcrumb-item>Bill</a-breadcrumb-item>
              </a-breadcrumb>
              <div :style="{ padding: '4px' }">
                <a>你好，李焕英</a>
                
              </div>
              <div :style="{ padding: '4px' }">
                <a>阿凡达</a>
              </div>
              <div :style="{ padding: '4px' }">
                <a>唐人街探案3</a>
              </div>
            </a-layout-content>
            <a-layout-footer style="text-align: center">
              Ant Design ©2018 Created by Ant UED
            </a-layout-footer>
          </a-layout>
        </a-col>
      </a-row>
    </div>
    <!-- <a-layout>
      <a-layout-header style="background: #fff; padding: 0" />
      <a-layout-content style="margin: 0 16px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>User</a-breadcrumb-item>
          <a-breadcrumb-item>Bill</a-breadcrumb-item>
        </a-breadcrumb>
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          Bill is a cat.
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout> -->
  </div>
</template>

<script>

export default {
  methods:{
    consolelog(){
      console.log("here is my home");
    }
  }
}
</script>

<style scoped>

</style>
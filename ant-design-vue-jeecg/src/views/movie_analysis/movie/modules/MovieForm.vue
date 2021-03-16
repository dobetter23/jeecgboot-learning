<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="电影名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieName']" placeholder="请输入电影名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="导演" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieDirector']" placeholder="请输入导演"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="编剧" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieScreenwriter']" placeholder="请输入编剧"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="主演" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieActors']" placeholder="请输入主演"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieType']" placeholder="请输入类型"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="语言" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieLanguage']" placeholder="请输入语言"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="地区、国家" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieRegion']" placeholder="请输入地区、国家"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="上映时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择上映时间" v-decorator="['movieDebut']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="时长" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieTime']" placeholder="请输入时长"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label=" 又名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieAlias']" placeholder="请输入 又名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="评分" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['movieGrade']" placeholder="请输入评分" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="封面/宣传图" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['movieCoverage']" placeholder="请输入封面/宣传图"></a-input>
            </a-form-item>
          </a-col>
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'MovieForm',
    components: {
      JFormContainer,
      JDate,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        form: this.$form.createForm(this),
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/com.dobetter23.movie/movie/add",
          edit: "/com.dobetter23.movie/movie/edit",
          queryById: "/com.dobetter23.movie/movie/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'movieName','movieDirector','movieScreenwriter','movieActors','movieType','movieLanguage','movieRegion','movieDebut','movieTime','movieAlias','movieGrade','movieCoverage'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'movieName','movieDirector','movieScreenwriter','movieActors','movieType','movieLanguage','movieRegion','movieDebut','movieTime','movieAlias','movieGrade','movieCoverage'))
      },
    }
  }
</script>
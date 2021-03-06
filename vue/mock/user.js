
const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  },
  user: {
    token: 'user-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'http://wx.qlogo.cn/mmopen/5gNE2LLibmx8t6DmRpRzbDVqSDstBnFMEJcDnFLibiamblSdQKaaKTYoMohkrxIY8YwicowK5YMnIn3Gs1icmeYibDMIibQFuiaDg1xq/64',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'http://wx.qlogo.cn/mmopen/5gNE2LLibmx8t6DmRpRzbDVqSDstBnFMEJcDnFLibiamblSdQKaaKTYoMohkrxIY8YwicowK5YMnIn3Gs1icmeYibDMIibQFuiaDg1xq/64',
    name: 'Normal Editor'
  },
  'user-token': {
    roles: ['user'],
    introduction: 'I am an user',
    avatar: 'http://wx.qlogo.cn/mmopen/5gNE2LLibmx8t6DmRpRzbDVqSDstBnFMEJcDnFLibiamblSdQKaaKTYoMohkrxIY8YwicowK5YMnIn3Gs1icmeYibDMIibQFuiaDg1xq/64',
    name: 'Normal User'
  }
}

module.exports = [
  // user login
  {
    url: '/vue-admin-template/user/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      const token = tokens[username]

      // mock error
      // todo:先让这里不管用户名是什么，都让它检查通过
      // if (!token) {
      //   return {
      //     code: 60204,
      //     message: 'Account and password are incorrect.'
      //   }
      // }

      return {
        code: 20000,
        data: token
      }
    }
  },

  // get user info
  {
    url: '/vue-admin-template/user/info\.*',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 20000,
        data: info
      }
    }
  },

  // user logout
  {
    url: '/vue-admin-template/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]

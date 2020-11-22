/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  // 这是预判断用户名是否有效的函数
  // const valid_map = ['admin', 'editor', 'user']
  // return valid_map.indexOf(str.trim()) >= 0
  return true
}

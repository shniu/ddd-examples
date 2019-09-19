import _ from 'lodash'

export const clearData = (data) => {
  if (_.isString(data)) {
    data = ''
  } else if (_.isPlainObject(data)) {
    for (let key in data) {
      data[key] = ''
    }
  }
  return data
}

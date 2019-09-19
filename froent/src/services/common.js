let fallbackStore = {}

export const Setitem = (key, val) => {
  try {
    localStorage.setItem(key, val)
  } catch (ex) {
    console.log('fallback to memory store')
    fallbackStore[key] = val
  }
}

export const Getitem = (key) => {
  try {
    return localStorage.getItem(key)
  } catch (ex) {
    return fallbackStore[key]
  }
}

export const Removeitem = (key) => {
  return localStorage.removeItem(key)
}

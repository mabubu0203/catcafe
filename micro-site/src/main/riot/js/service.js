const service = (store) => {
    const welcome = () => {
        store.update({
            isStoreSelectedIn: true
        })
    }
    const logout = () => {
        store.update({
            isStoreSelectedIn: false
        })
    }

    return {
        login: welcome,
        logout: logout
    }
}

export default service
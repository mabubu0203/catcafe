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
        storeSelect: welcome,
        logout: logout
    }
}

export default service
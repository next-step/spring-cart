const addCartItem = (productId) => {
    const credentials = localStorage.getItem('credentials');
    if (!credentials) {
        alert('사용자 정보가 없습니다.');
        window.location.href = '/users/settings';
        return;
    }

    const option = {
        headers: {
            'Authorization': `Basic ${credentials}`
        }}

    axios.post('/carts/' + productId,'',option
    ).then((response) => {
        alert('장바구니에 담았습니다.');
    }).catch((error) => {
        console.error(error);
    });
}

const removeCartItem = (id) => {
    const credentials = localStorage.getItem('credentials');
    if (!credentials) {
        alert('사용자 정보가 없습니다.');
        window.location.href = '/users/settings';
        return;
    }

    axios.delete('/carts/' + id, '',
        {
            headers: {
                'Authorization': `Basic ${credentials}`
            }
        }).then((response) => {
        window.location.reload();
    }).catch((error) => {
        console.error(error);
    });
}

import { useEffect, useState } from "react";
import Link from "next/link"
import { v4 as uuidv4 } from 'uuid';
import styles from "../styles/Products.module.css"


interface ratt {
    rate: number,
    count: number
}

interface product {
    category: string,
    description: string,
    id: number,
    image: string,
    price: number,
    rating: ratt,
    title: string


}

export default function Products(): any {
    const [products, setProducts] = useState<Array<product>>([])
    const [quant, setQuant] = useState<{ [id: number]: number }>({})
    const [nItens, setItens] = useState<number>(0)
    const [itemId, setItemId] = useState<number>(1)
    const [userId, setUserId] = useState<any>()

    const axios = require('axios')

    async function getProduct() {
        try {
            const response = await axios.get("https://fakestoreapi.com/products?limit=12");
            return response.data;
        } catch (error) {
            console.error(error);
            return [];
        }
    }

    function incrementQuantity(productId: number) {
        setItemId(productId)
        setQuant(prevQuantities => ({
            ...prevQuantities,
            [productId]: (prevQuantities[productId] || 0) + 1
        }));

        console.log(quant[productId])
        setItens(quant[productId])

    }

    function decrementQuantity(productId: number) {
        setItemId(productId);
        setQuant(prevQuantities => {
            const updatedQuantities = { ...prevQuantities };
            if (updatedQuantities[productId] && updatedQuantities[productId] > 0) {
                updatedQuantities[productId] -= 1;
            }
            return updatedQuantities;
        });
    }

    function objectCard(att: number, idUser: string, price: number) {



        axios.post("http://localhost:8081/cart", {
            "userId": idUser,
            "productId": itemId,
            "nItens": att,
            "price": price
        }).then(function (response: any) {
            console.log("response aqui do carrinho com user automatico")
            console.log(response);
        }).catch(function (error: any) {
            console.log(error)
        })
    }

    async function findUser() {
        try {
            const user = await axios.get(`http://localhost:8081/user/marcos@gmail.com`)
            const userId = user.data.id;
            setUserId(userId)


        } catch (error) {
            console.error(error)

        }

    }

    useEffect(() => {
        findUser()
        getProduct().then(data => setProducts(data))
    }, [])

    return (
        <>
            <header className={styles.main}>
                <div className={styles.products}>
                    {
                        products.map((product: product) => (
                            <div className={styles.item} key={product.id}>
                                <h2 >{product.title}</h2>
                                <p>{product.description}</p>
                                <img src={product.image} alt={product.title} height={250} width={200} />
                                <p>Price: ${product.price}</p>
                                <p>Category: {product.category}</p>
                                <p>Rating: {product.rating.rate} ({product.rating.count} votes)</p>
                                <p>Quantity: {((quant[product.id] || 0) - 1) == -1 ? 0 : ((quant[product.id] || 0) - 1)}</p>
                                <div className={styles.max}>
                                    <button onClick={() => decrementQuantity(product.id)}>-</button>
                                    <button onClick={() => incrementQuantity(product.id)}>+</button>
                                </div>
                                <Link href="/cart"><button className={styles.btt} onClick={() => { objectCard(nItens, userId, product.price); }}>Comprar</button></Link>
                            </div>
                        ))
                    }
                </div>
            </header>
        </>

    )
}
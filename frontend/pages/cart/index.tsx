import axios from 'axios'
import { useEffect, useState } from 'react'
import Link from "next/link"
import NavBar from '../../components/navBar'
import Product from '../../components/product'
import Footer from '../../components/footer'

interface cart {
    cartId: number,
    userId: string,
    productId: number,
    nItens: number
}

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

export default function cart() {

    return (
        <>
            <NavBar />
            <Product />
            <Footer />
        </>
    )
}
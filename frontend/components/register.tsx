import { useState } from "react";
import styles from "../styles/Register.module.css";
import axios from 'axios'
import Link from "next/link"

interface FormData {
    name: string;
    email: string;
    senha: string;
}

export default function Register() {
    const [formData, setFormData] = useState<FormData>({
        name: "",
        email: "",
        senha: ""
    });

    const handleChange = (e:any) => {
        const { name, value, email } = e.target;
        console.log(e.target.value);
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
        console.log(formData)

    };

    const handleSubmit = async () => {
        try {
            await axios.post("http://0.0.0.0:8081/user", formData)
    
        } catch (error) {
            console.error('Erro ao enviar formulário:', formData);
        }
    };

    return (
        <div className={styles.registerForm}>
            <h2>Registro</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">Nome:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        autoComplete="current-text"


                    />
                </div>
                <div>
                    <label htmlFor="email">E-mail:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        autoComplete="current-email"
                    />
                </div>
                <div>
                    <label htmlFor="senha">Senha:</label>
                    <input
                        type="password"
                        id="senha"
                        name="senha"
                        value={formData.senha}
                        onChange={handleChange}
                        autoComplete="current-password"
                    />
                </div>
            </form>
            <Link href="/home"><button onClick={handleSubmit}>Registrar</button></Link>
        </div>
    );
};

import "./App.css";
import { CartaoProduto } from "./componentes/CartaoProduto";
import { useProdutoDados } from "./hooks/useProdutoDados";
import {FormularioProduto} from "./componentes/FormularioProduto.tsx";
import { useState } from "react";
import type { ProdutoDados } from "./interfaces/ProdutoDados.ts";

function App() {
    const { data} = useProdutoDados();
    const [modalAberto, setModalAberto] = useState(false);
    const [produtoParaEditar, setProdutoParaEditar] = useState<ProdutoDados | null>(null);
    const [somenteLeitura, setSomenteLeitura] = useState(false);

    function handleEditar(produto: ProdutoDados) {
        setProdutoParaEditar(produto);
        setSomenteLeitura(false);
        setModalAberto(true);
    }

    function handleVisualizar(produto: ProdutoDados) {
        setProdutoParaEditar(produto);
        setSomenteLeitura(true);
        setModalAberto(true);
    }

    function handleFecharModal() {
        setModalAberto(false);
        setProdutoParaEditar(null);
        setSomenteLeitura(false);
    }

    return (
        <main className="container">
            <div className="cabecalho">
                <h1>Xis do Ramires</h1>
                <button className="btn-novo-produto" onClick={() => setModalAberto(true)}>
                    + Novo Produto
                </button>
            </div>

            {
                modalAberto && (

                    <div className="overlay">

                        <div className="modal">

                            <button
                                className="fechar"
                                onClick={handleFecharModal}
                            >
                                X
                            </button>

                            <FormularioProduto produtoParaEditar={produtoParaEditar} onFechar={handleFecharModal} somenteLeitura={somenteLeitura} />

                        </div>

                    </div>

                )
            }

            <section className="grade-cartoes">
                {data?.map((produto) => (
                    <CartaoProduto key={produto.id} produto={produto} onEditar={handleEditar} onVisualizar={handleVisualizar} />
                ))}

            </section>
        </main>
    );
}

export default App;
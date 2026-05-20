import {useProdutoDados} from "./hooks/UseProduto.ts";
import {CartaoProduto} from "./componente/Card.tsx";
import "./App.css";

function App() {
    const { data, isLoading, isError } = useProdutoDados();

    return (
        <main className="container">
            <h1>MenuStream - Produtos</h1>

            {isLoading && <p>Carregando produtos...</p>}

            {isError && (
                <p className="mensagem-erro">
                    Erro ao carregar os produtos. Verifique se o backend está rodando.
                </p>
            )}

            <section className="grade-cartoes">
                {data?.map((produto) => (
                    <CartaoProduto key={produto.id} produto={produto} />
                ))}
            </section>
        </main>
    );
}

export default App;


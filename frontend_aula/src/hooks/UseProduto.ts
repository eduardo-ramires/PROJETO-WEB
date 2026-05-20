import axios from "axios";
import { useQuery, type UseQueryResult } from "@tanstack/react-query";
import type {ProdutoDados} from "../interface/Produto.ts";

const API_URL = "http://localhost:8080";

const buscarDados: () => Promise<ProdutoDados[]> = async (): Promise<ProdutoDados[]> => {
    const response = await axios.get<ProdutoDados[]>(`${API_URL}/api/produtos`);
    return response.data;
};

export function useProdutoDados(): UseQueryResult<ProdutoDados[], Error> {
    return useQuery({
        queryKey: ["produto-dados"], queryFn: buscarDados, retry: 2,
    });
}
